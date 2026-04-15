/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

export default function App() {
  return (
    <div className="min-h-screen bg-zinc-950 text-zinc-100 p-8 font-sans">
      <div className="max-w-2xl mx-auto">
        <h1 className="text-4xl font-bold mb-4 text-emerald-400">LexiLearn Android</h1>
        <p className="text-xl text-zinc-400 mb-8">
          This is a native Android application project. The source code is located in the 
          <code className="bg-zinc-800 px-2 py-1 rounded mx-1">app/</code> directory.
        </p>
        
        <div className="bg-zinc-900 border border-zinc-800 rounded-xl p-6 space-y-4">
          <h2 className="text-2xl font-semibold">Project Structure (Stage 1)</h2>
          <ul className="list-disc list-inside space-y-2 text-zinc-300">
            <li><span className="font-mono text-emerald-500">app/src/main/java/</span> - Kotlin Source Code</li>
            <li><span className="font-mono text-emerald-500">app/src/main/res/</span> - XML Layouts & Resources</li>
            <li><span className="font-mono text-emerald-500">data/local/</span> - Room Database & DAO</li>
            <li><span className="font-mono text-emerald-500">ui/news/</span> - MVVM News Screen Implementation</li>
          </ul>
        </div>
        
        <div className="mt-8 p-4 bg-emerald-900/20 border border-emerald-800/50 rounded-lg">
          <p className="text-emerald-300">
            <strong>Note:</strong> To run this app, import the project into <strong>Android Studio</strong>. 
            The web preview here only shows this informational page.
          </p>
        </div>
      </div>
    </div>
  );
}
